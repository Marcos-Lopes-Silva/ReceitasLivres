import { Outlet } from 'react-router-dom';
import stylesTema from 'styles/Tema.module.scss';
import styles from './PaginaPadrao.module.scss';

export default function PaginaPadrao() {
  return (
    <>
      <header className={styles.header}>
        <div className={styles.header__text}>
          Suas receitas com um gostinho de React
        </div>
      </header>
      <div className={stylesTema.container}>
        <Outlet />
      </div>
    </>
  );
}