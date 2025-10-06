import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Recuperar() {
  const [formData, setFormData] = useState({ correo: "", direccion: "" });
  const [mensaje, setMensaje] = useState("");
  const [token, setToken] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/clientes/solicitar-recuperacion", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      const data = await response.json();

      if (response.ok) {
        setMensaje(data.mensaje);
        setToken(data.token);
      } else {
        setMensaje(data || "❌ Error al recuperar contraseña.");
      }
    } catch (error) {
      setMensaje("⚠️ Error de conexión con el servidor.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-2xl shadow-2xl w-full max-w-md text-center">
        <h2 className="text-2xl font-bold text-gray-800 mb-2">Recuperar Contraseña</h2>
        <p className="text-gray-500 mb-6">Ingresa tu correo y dirección registrada</p>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="email"
            name="correo"
            placeholder="Correo electrónico"
            value={formData.correo}
            onChange={handleChange}
            required
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />
          <input
            type="text"
            name="direccion"
            placeholder="Dirección registrada"
            value={formData.direccion}
            onChange={handleChange}
            required
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />
          <button
            type="submit"
            className="w-full bg-red-600 text-white py-2 rounded-full hover:bg-red-700 transition"
          >
            Generar token
          </button>
        </form>

        {mensaje && <p className="mt-4 text-gray-700">{mensaje}</p>}

        {token && (
          <div className="mt-4 bg-gray-100 p-4 rounded-lg text-left">
            <p className="font-semibold text-gray-800">Tu token generado:</p>
            <p className="text-sm text-gray-600 break-all">{token}</p>
            <button
              onClick={() => navigate("/restablecer")}
              className="mt-4 w-full bg-green-600 text-white py-2 rounded-full hover:bg-green-700 transition"
            >
              Ir a restablecer contraseña
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default Recuperar;

