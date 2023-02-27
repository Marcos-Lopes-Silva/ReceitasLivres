import cardapio from 'data/cardapio.json';
import stylesTema from 'styles/Tema.module.scss';
import styles from './Home.module.scss';
export default function Home() {
  let pratosSemana = [...cardapio];
  pratosSemana = pratosSemana.sort(() => 0.5 - Math.random()).splice(0, 5);

  return (
    <section>
      <h3 className={stylesTema.titulo}>Recomendações da Semana</h3>
      <div className={styles.recomendados}>
        {pratosSemana.map(item => (
          <div key={item.id} className={styles.recomendado}>
            <div className={styles.recomendado_imagem}>
              <img src={item.photo} alt={item.title} />
            </div>
            <button className={styles.recomendado__botao}>
              Ver mais
            </button>
          </div>
        ))}
      </div>
    </section>
  );
}