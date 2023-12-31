import { CgLock, CgMail } from 'react-icons/cg';
import styles from './Login.module.scss';
import { useAuth } from 'context/AuthProvider/useAuth';
import { useNavigate, Link } from 'react-router-dom';
import { useEffect } from 'react';
import { toast } from 'react-toastify';


export default function Login() {

  const auth = useAuth();
  const navigate = useNavigate();





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
      toast.success('Login realizado com sucesso!');
      navigate('/');
    } catch (error) {
      toast.error('Usuário ou senha incorretos.')
    }
  }

  useEffect(() => {
    auth.login ? navigate('/') : navigate('/login');
  }, []);

  return (

    <div className={styles.section}>
      <div className={styles.section__formbox}>
        <form onSubmit={handleLogin} >
          <h2>Login</h2>
          <div className={styles.section__inputbox}>
            <CgMail className={styles.section__icon} size={20} color="#4C4D5E" />
            <input className={styles.section__input} type="email" name='email' required />
            <label htmlFor="">Email</label>
          </div>
          <div className={styles.section__inputbox}>
            <CgLock className={styles.section__icon} size={20} color="#4C4D5E" />
            <input className={styles.section__input} type="password" name='password' required />
            <label htmlFor="">Senha</label>
          </div>
          <div className={styles.section__rememberpass}>
            <label htmlFor=""><input type="checkbox" />Lembre de mim</label>
            <Link to={'/cadastrar'}>
              Criar uma conta
            </Link>
          </div>
          <button type='submit' >Log in</button>
        </form>
      </div>
    </div>
  );
}