import Buscador from "components/Buscador";
import Filtros from "components/Filtros";
import Navbar from "components/Navbar";
import { useState } from 'react';
import Itens from './Itens';
import Ordenador from "./Ordenador";
import styles from "./Receita.module.scss";

export default function Receitas() {
    const [busca, setBusca] = useState("");
    const [filtro, setFiltro] = useState<number | null>(null);
    const [ordenador, setOrdenador] = useState("");
    return (
        <main className={styles.menu}>
            <Navbar>
                <section className={styles.receitas}>

                    <h3 className={styles.receitas__titulo}>Receitas</h3>
                    <Buscador busca={busca} setBusca={setBusca} />
                    <div className={styles.cardapio__filtros}>
                        <Filtros filtro={filtro} setFiltro={setFiltro} />
                        <Ordenador ordenador={ordenador} setOrdenador={setOrdenador} />
                    </div>
                    <Itens />
                </section>
            </Navbar>
        </main>
    )
}