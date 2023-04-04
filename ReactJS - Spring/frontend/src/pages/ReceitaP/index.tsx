import Axios from 'axios';
import styles from './Receita.module.scss';
import { useParams } from 'react-router-dom';
import { IReceita } from 'types/types';
import { useState, useEffect } from 'react';
import { getReceitas } from 'context/AuthProvider/utils';

export default function ReceitaP() {

  const { id } = useParams();
  const [receita, setReceita] = useState<IReceita>();
  const [ingredientes, setIngredientes] = useState([]);

  useEffect(() => {

    fetchData();

  }, [id]);


  const fetchData = async () => {
    const data = getReceitas(id);
    console.log(data);
    setReceita(await data);
  };




  function listIngredientes(str: string) {
    const regex = '+[^~][^;]/g';
    const array = str.match(regex);
    console.log(array);
    array?.map((valor) => {
      valor = valor.replace(';', '');
      array?.push(valor);
    });
    console.log(array);

  }



  return (
    <div className={styles.cont}>
      <div className={styles.item}>
        <div className={styles.item__titulo} >
          <h2>{receita?.titulo}</h2>
          <a>por {receita?.usuario.nome}</a>
        </div>
        <hr className={styles.item__hr} />
        <div className={styles.item__imagem} >
          <img src={receita?.urlImage} alt={receita?.titulo} />
        </div>
        <div className={styles.item__tags}>
          <div className={`${styles.item__tags__tipo} ${styles[`item__tags__tipo__${receita?.categoria.nome.toLowerCase()}`]}`}>
            <a>{receita?.categoria.nome}</a>
          </div>
          <div className={styles.item__porcao}>
            <a>Quantidade: {receita?.size} </a>
          </div>
          <div className={styles.item__qtdpessoas}>
            <a>Serve {receita?.serve} pessoa{receita?.serve === 1 ? '' : 's'}</a>
          </div>
        </div>
        <div className={styles.item__ingredientes}>
          <h1>Ingredientes</h1>
          <ul>
            <li></li>
          </ul>
        </div>
        <div>

        </div>
      </div>
    </div>
  );
}