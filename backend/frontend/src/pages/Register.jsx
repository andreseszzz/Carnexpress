import { useState } from "react";
import { useNavigate } from "react-router-dom"; // ✅ Importamos el hook para redirigir

function Register() {
  const [formData, setFormData] = useState({
    nombre: "",
    correo: "",
    direccion: "",
    telefono: "",
    contrasena: "",
  });

  const [mensaje, setMensaje] = useState("");
  const navigate = useNavigate(); // ✅ Inicializamos el hook de navegación

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/clientes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setMensaje("✅ Registro exitoso, redirigiendo al login...");
        setFormData({
          nombre: "",
          correo: "",
          direccion: "",
          telefono: "",
          contrasena: "",
        });

        // ✅ Espera 2 segundos y redirige automáticamente al login
        setTimeout(() => {
          navigate("/");
        }, 2000);
      } else if (response.status === 409) {
        setMensaje("⚠️ Ya existe un usuario con ese correo");
      } else {
        setMensaje("❌ Error al registrar usuario");
      }
    } catch (error) {
      console.error("Error:", error);
      setMensaje("⚠️ Error de conexión con el servidor");
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
        <h2 className="text-2xl font-bold text-gray-800 mb-2">Crear cuenta</h2>
        <p className="text-gray-500 mb-6">Regístrate para continuar</p>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            placeholder="Nombre completo"
            required
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />

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
            type="text"
            name="direccion"
            value={formData.direccion}
            onChange={handleChange}
            placeholder="Dirección"
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />

          <input
            type="text"
            name="telefono"
            value={formData.telefono}
            onChange={handleChange}
            placeholder="Teléfono"
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
            Registrarse
          </button>
        </form>

        {mensaje && <p className="mt-4 text-gray-700">{mensaje}</p>}

        {/* Enlace de vuelta al login */}
        <p className="mt-6 text-sm text-gray-500">
          ¿Ya tienes cuenta?{" "}
          <button
            onClick={() => navigate("/")}
            className="text-red-600 font-semibold hover:underline"
          >
            Inicia sesión aquí
          </button>
        </p>
      </div>
    </div>
  );
}

export default Register;
