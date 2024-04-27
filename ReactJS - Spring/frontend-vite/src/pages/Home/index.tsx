import { useEffect, useState } from 'react';
import stylesTema from '@/styles/Tema.module.scss';
import styles from './Home.module.scss';
import { IReceita } from '@/types/types';
import { Request } from '@/context/AuthProvider/utils';
import { Link } from 'react-router-dom';

export default function Home() {

  const [lista, setLista] = useState<IReceita[]>([]);

  const fetchData = async () => {
    const data = await Request('receitas', 'get');

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
            <Link to={`/receita/${item.id}`}>
              <button className={styles.recomendado__botao}>
                Ver mais
              </button>
            </Link>
          </div>
        ))}
      </div>
    </section>
  )
}