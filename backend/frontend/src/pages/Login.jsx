import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const [formData, setFormData] = useState({
    correo: "",
    contrasena: "",
  });

  const [mensaje, setMensaje] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/clientes/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const cliente = await response.json();

        setMensaje(`✅ Bienvenido, ${cliente.nombre}! Redirigiendo...`);

        // ✅ Guardar sesión localmente
        localStorage.setItem("cliente", JSON.stringify(cliente));

        // ✅ Redirigir después de 2 segundos
        setTimeout(() => {
          navigate("/catalogo");
        }, 2000);
      } else if (response.status === 401) {
        setMensaje("❌ Credenciales incorrectas. Intenta nuevamente.");
      } else {
        setMensaje("⚠️ Error al conectar con el servidor.");
      }
    } catch (error) {
      console.error("Error:", error);
      setMensaje("⚠️ Error de conexión con el servidor.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-2xl shadow-2xl w-full max-w-md text-center">
        <img
          src="/logo.png"
          alt="Carnexpress Logo"
          className="mx-auto mb-4 w-20"
        />
        <h2 className="text-2xl font-bold text-gray-800 mb-2">
          Iniciar sesión
        </h2>
        <p className="text-gray-500 mb-6">Ingresa tus datos para continuar</p>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="email"
            name="correo"
            value={formData.correo}
            onChange={handleChange}
            placeholder="Correo electrónico"
            required
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />

          <input
            type="password"
            name="contrasena"
            value={formData.contrasena}
            onChange={handleChange}
            placeholder="Contraseña"
            required
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />

          <button
            type="submit"
            className="w-full bg-red-600 text-white py-2 rounded-full hover:bg-red-700 transition"
          >
            Ingresar
          </button>

          {/* 🆕 Enlace a recuperación de contraseña */}
          <p className="mt-3 text-sm text-gray-500">
            ¿Olvidaste tu contraseña?{" "}
            <button
              onClick={() => navigate("/recuperar")}
              className="text-red-600 font-semibold hover:underline"
              type="button"
            >
              Recupérala aquí
            </button>
          </p>
        </form>

        {mensaje && <p className="mt-4 text-gray-700">{mensaje}</p>}

        {/* Enlace a registro */}
        <p className="mt-6 text-sm text-gray-500">
          ¿No tienes cuenta?{" "}
          <button
            onClick={() => navigate("/register")}
            className="text-red-600 font-semibold hover:underline"
            type="button"
          >
            Regístrate aquí
          </button>
        </p>
      </div>
    </div>
  );
}

export default Login;

