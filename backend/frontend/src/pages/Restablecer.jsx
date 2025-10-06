import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Restablecer() {
  const [formData, setFormData] = useState({ token: "", nuevaContrasena: "" });
  const [mensaje, setMensaje] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/clientes/restablecer", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      const text = await response.text();
      setMensaje(text);

      if (response.ok) {
        setTimeout(() => navigate("/"), 2500); // Redirige al login
      }
    } catch (error) {
      setMensaje("⚠️ Error al conectar con el servidor.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-2xl shadow-2xl w-full max-w-md text-center">
        <h2 className="text-2xl font-bold text-gray-800 mb-2">Restablecer Contraseña</h2>
        <p className="text-gray-500 mb-6">Usa el token que generaste anteriormente</p>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="token"
            placeholder="Token de recuperación"
            value={formData.token}
            onChange={handleChange}
            required
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />
          <input
            type="password"
            name="nuevaContrasena"
            placeholder="Nueva contraseña"
            value={formData.nuevaContrasena}
            onChange={handleChange}
            required
            className="w-full px-4 py-2 border rounded-full focus:ring-2 focus:ring-red-500"
          />
          <button
            type="submit"
            className="w-full bg-red-600 text-white py-2 rounded-full hover:bg-red-700 transition"
          >
            Cambiar contraseña
          </button>
        </form>

        {mensaje && <p className="mt-4 text-gray-700">{mensaje}</p>}
      </div>
    </div>
  );
}

export default Restablecer;

