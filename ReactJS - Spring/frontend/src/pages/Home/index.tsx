import Axios from 'axios';
import { useEffect, useState } from 'react';
import stylesTema from 'styles/Tema.module.scss';
import styles from './Home.module.scss';
import { IReceita } from 'types/types';

export default function Home() {

  const [lista, setLista] = useState<IReceita[]>([]);

  const fetchData = async () => {
    const { data } = await Axios.get('http://localhost:8080/receitas');

    const receitas = data.content;

    setLista(receitas);
  };

  useEffect(() => {
    fetchData();
  }, []);


  let pratosSemana = [...lista];
  pratosSemana = pratosSemana.sort(() => 0.5 - Math.random()).splice(0, 5);

  return (
    <section>
      <h3 className={stylesTema.titulo}>Recomendações da Semana</h3>
      <div className={styles.recomendados}>
        {pratosSemana.map(item => (
          <div key={item.id} className={styles.recomendado}>
            <div className={styles.recomendado_imagem}>
              <img src={item.urlImage} alt={item.titulo} />
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