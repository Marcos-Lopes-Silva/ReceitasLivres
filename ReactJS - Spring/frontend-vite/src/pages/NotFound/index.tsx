import  NotFound_Image from '@/assets/not_found.svg';
import classNames from 'classnames';
import { useNavigate } from 'react-router-dom';
import stylesTema from '@/styles/Tema.module.scss';
import styles from './NotFound.module.scss';

export default function NotFound() {
  const navigate = useNavigate();
  return (
    <div className={classNames({
      [styles.container]: true,
      [stylesTema.container]: true

    })}>
      <div className={styles.voltar}>
        <button onClick={() => navigate(-1)}>
          {'< Voltar'}
        </button>
      </div>
      <NotFound_Image />
    </div>
  );
}