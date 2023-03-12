import { CgLock, CgMail } from 'react-icons/cg';
import styles from './Login.module.scss';


export default function Login() {
  return (

    <section className={styles.section}>
      <div className={styles.formbox}>
        <div className="form-value">
          <form action="">
            <h2>Login</h2>
            <div className="inputbox">
              <CgMail size={20} color="#4C4D5E" />

              <input type="email" required />

              <label htmlFor="">Email</label>
            </div>
            <div className={styles.formbox__inputbox}>
              <CgLock size={20} color="#4C4D5E" />

              <input type="password" required />

              <label htmlFor="">Senha</label>
            </div>
            <div>
              <label htmlFor=""><input type="checkbox" />Lembre de mim</label>
              <a href="#">Esqueci minha senha</a>
            </div>
            <button>Entrar</button>
          </form>
        </div>
      </div>
    </section>
  );
}