import styles from './Receita.module.scss';
import { useParams } from 'react-router-dom';
import { IReceita } from 'types/types';
import { useState, useEffect } from 'react';
import { Request } from 'context/AuthProvider/utils';

interface Ingrediente {
  index: number;
  input: string;
}


export default function ReceitaP() {

  const { id } = useParams();
  const [receita, setReceita] = useState<IReceita>();
  const [ingredientes, setIngredientes] = useState<Ingrediente[]>([]);

  useEffect(() => {

    fetchData();

  }, [id]);


  const fetchData = async () => {
    const data = await Request('receitas', 'get', id);
    setReceita(data);
    listIngredientes(data.ingredientes);
  };




  function listIngredientes(str: string) {
    const array = str.split(';');
    const arr: Ingrediente[] = [];
    array.map((item) => {
      arr.push({ index: arr.length, input: item });
    });
    setIngredientes(arr);
  }



  return (
    <div className={styles.cont}>
      <div className={styles.item}>
        <div className={styles.item__titulo} >
          <h2>{receita ? receita.titulo[0].toUpperCase() + receita.titulo.substring(1) : null}</h2>
          <a>por {receita?.usuario.nome}</a>
        </div>
        <hr className={styles.item__hr} />
        <div className={styles.item__imagem} >
          <img src={receita?.urlImage} alt={receita?.titulo} />
        </div>
        <div className={styles.item__tags}>
          <div className={styles.item__tags__centraliza}>
            <label>Tipo</label>
            <div className={`${styles.item__tags__tipo} ${styles[`item__tags__tipo__${receita?.categoria.nome.toLowerCase()}`]}`}>
              <a>{receita?.categoria.nome}</a>
            </div>
          </div>
          <div className={styles.item__tags__centraliza}>
            <label>Porção</label>
            <div className={styles.item__tags__size}>
              <a>{receita?.size}g</a>
            </div>
          </div>
          <div className={styles.item__tags__centraliza}>
            <label>Serve</label>
            <div className={styles.item__tags__serve}>
              <a>{receita?.serve}</a>
            </div>
          </div>

        </div>
        <div className={styles.item__ingredientes}>
          <h1>Ingredientes</h1>
          <ul>
            {ingredientes.length > 0 ? (ingredientes.map((ingrediente: Ingrediente) => (
              <li key={ingrediente.index} className={styles.item__ingredientes__ingrediente}>
                <label>
                  {ingrediente.input[0].toUpperCase() + ingrediente.input.substring(1)}
                </label>
              </li>
            ))) :
              <label>Não foram salvos ingredientes</label>
            }
          </ul>

        </div>
        <div className={styles.item__modopreparo}>
          <h1>Modo de Preparo</h1>
          <div>
            <a>{receita?.modoPreparo}</a>
          </div>
        </div>
      </div>
    </div>
  )
}