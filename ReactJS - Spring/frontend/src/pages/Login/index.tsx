import { CgLock, CgMail } from 'react-icons/cg';
import styles from './Login.module.scss';
import { useAuth } from 'context/AuthProvider/useAuth';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { getUserLocalStorage } from 'context/AuthProvider/utils';


export default function Login() {

  const auth = useAuth();
  const user = getUserLocalStorage();
  const navigate = useNavigate();
  const errorMessage = () => alert('Email ou senha incorretos');




  async function handleLogin(e: React.SyntheticEvent) {
    e.preventDefault();
    const target = e.target as typeof e.target & {
      email: { value: string };
      password: { value: string };
    };
    const email = target.email.value;
    const password = target.password.value;

    try {

      await auth.authenticate(email, password);

      navigate('/');
    } catch (error) {
      errorMessage();
    }
  }

  useEffect(() => {
    user ? navigate('/') : navigate('/login');
  }, []);

  return (

    <section className={styles.section}>
      <div className={styles.formbox}>
        <div className="form-value">
          <form className={styles.form} onSubmit={handleLogin} >
            <h2>Login</h2>
            <div className="inputbox">
              <CgMail size={20} color="#4C4D5E" />

              <input type="email" name='email' required />

              <label htmlFor="">Email</label>
            </div>
            <div className={styles.formbox__inputbox}>
              <CgLock size={20} color="#4C4D5E" />

              <input type="password" name='password' required />

              <label htmlFor="">Senha</label>
            </div>
            <div>
              <label htmlFor=""><input type="checkbox" />Lembre de mim</label>
              <a href="#">Esqueci minha senha</a>
            </div>
            <button type='submit' >Log in</button>
          </form>
        </div>
      </div>
    </section>
  );
}