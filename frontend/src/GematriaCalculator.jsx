import { useState } from "react";
import { computeGematria } from "./api";


const ALL = ["SIMPLE", "SIDURI", "ATBASH", "ALBAM"];

export default function GematriaCalculator() {
  const [text, setText] = useState("");
  const [methods, setMethods] = useState(ALL);
  const [loading, setLoading] = useState(false);
  const [resp, setResp] = useState(null);
  const [err, setErr] = useState(null);

  const toggle = (m) => {
    setMethods((prev) =>
      prev.includes(m) ? prev.filter((x) => x !== m) : [...prev, m]
    );
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    setErr(null);
    setResp(null);
    setLoading(true);
    try {
      const data = await computeGematria(text, methods);
      setResp(data);
    } catch (ex) {
      setErr(ex?.response?.data?.message || ex.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 720, margin: "40px auto", fontFamily: "system-ui" }}>
      <h1>Calculateur de Guematria</h1>

      <form onSubmit={onSubmit} style={{ display: "grid", gap: 12 }}>
        <label>
          Nom / mot (hébreu) :
          <input
            value={text}
            onChange={(e) => setText(e.target.value)}
            placeholder="ex: שלום"
            style={{ width: "100%", padding: 10, fontSize: 18 }}
          />
        </label>

        <div style={{ display: "flex", gap: 12, flexWrap: "wrap" }}>
          {ALL.map((m) => (
            <label key={m} style={{ display: "flex", gap: 6, alignItems: "center" }}>
              <input
                type="checkbox"
                checked={methods.includes(m)}
                onChange={() => toggle(m)}
              />
              {m}
            </label>
          ))}
        </div>

        <button disabled={loading || !text.trim()} style={{ padding: 10 }}>
          {loading ? "Calcul..." : "Calculer"}
        </button>
      </form>

      {err && <p style={{ color: "crimson" }}>{err}</p>}

      {resp && (
        <div style={{ marginTop: 24 }}>
          <h2>Résultats</h2>
          <pre style={{ background: "#f6f6f6", padding: 12, overflow: "auto" }}>
            {JSON.stringify(resp, null, 2)}
          </pre>

          <ul>
            {resp.results.map((r) => (
              <li key={r.method}>
                <b>{r.method}</b> : {r.value}
                {r.transformedText ? ` (transformé: ${r.transformedText})` : ""}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}
