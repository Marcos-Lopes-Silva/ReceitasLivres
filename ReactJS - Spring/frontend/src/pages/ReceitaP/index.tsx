import Axios from 'axios';
import styles from './Receita.module.scss';
import { useParams } from 'react-router-dom';
import { Receita } from 'types/TReceita';
import { useState, useEffect } from 'react';
import { stringify } from 'querystring';

export default function ReceitaP() {

  const { id } = useParams();
  const [receita, setReceita] = useState<Receita>();
  const [ingredientes, setIngredientes] = useState([]);


  useEffect(() => {
    fetchData();
  }, [id]);

  function listIngredientes(str: string) {
    const regex = '\w+[^~][^;]/g';
    array2: [];
    var array = str.match(regex);
    console.log(array);
    array?.map((valor) => {
      valor = valor.replace(';', '');
      // array2 = array?.push(valor);
    });


  }

  const fetchData = async () => {
    const { data } = await Axios.get(`http://localhost:8080/receitas/${id}`);
    const receitas = data;
    console.log(receitas);
    setReceita(receitas);
  };

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