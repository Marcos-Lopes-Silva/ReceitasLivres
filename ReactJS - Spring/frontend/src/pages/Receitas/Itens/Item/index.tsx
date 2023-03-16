import styles from './Item.module.scss';


export interface Receitas {
  sort?(arg0: (a: any, b: any) => 1 | -1): Function;

  id: number;
  titulo: string;
  modoPreparo: string;
  ingredientes: string;
  size: number;
  usuario: number;
  urlImage: string;
  descricao: string;
  category: {
    id: number;
    label: string
  }
  serve: number
}

export default function Item(props: Receitas) {

  const { titulo, modoPreparo, ingredientes, size, usuario, urlImage, descricao, category, serve } = props;
  return (
    <div className={styles.item}>
      <div className={styles.item__imagem}>
        <img src={urlImage} alt={titulo} />
      </div>
      <div className={styles.item__descricao}>
        <div className={styles.item__titulo}>
          <h2>{titulo}</h2>
          <p>{descricao}</p>
        </div>
        <div className={styles.item__tags}>
          <div className={`${styles.item__tipo} ${styles[`item__tipo__${category.label.toLowerCase()}`]}`}>
            {category.label}
          </div>
          <div className={styles.item__porcao}>
            {size}g
          </div>
          <div className={styles.item__qtdpessoas}>
            Serve {serve} pessoa{serve === 1 ? '' : 's'}
          </div>
        </div>
      </div>
    </div>
  );
}

