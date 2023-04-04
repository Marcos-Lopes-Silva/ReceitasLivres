import styles from './Item.module.scss';
import { IReceita } from 'types/types';
import { Link } from 'react-router-dom';



export default function Item(props: IReceita) {


  const { titulo, size, urlImage, descricao, categoria, serve } = props;
  return (
    <div className={styles.item}>
      <div className={styles.item__imagem} >
        <Link to={`/receita/${props.id}`}>
          <img src={urlImage} alt={titulo} />
        </Link>

      </div>
      <div className={styles.item__descricao}>
        <div className={styles.item__titulo} >
          <h2 className={styles.item__titulo__h2}> <Link to={`/receita/${props.id}`}>{titulo}</Link></h2>
          <p>{descricao}</p>
        </div>
        <div className={styles.item__tags}>
          <div className={`${styles.item__tipo} ${styles[`item__tipo__${categoria.nome.toLowerCase()}`]}`}>
            {categoria.nome}
          </div>
          <div className={styles.item__porcao}>
            {size}
          </div>
          <div className={styles.item__qtdpessoas}>
            Serve {serve} pessoa{serve === 1 ? '' : 's'}
          </div>
        </div>
      </div>
    </div>
  );
}

