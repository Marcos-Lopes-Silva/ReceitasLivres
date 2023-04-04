import logo from 'assets/logo.png';
import { Link } from 'react-router-dom';
import styles from './Navbar.module.scss';
import { useEffect, useState } from 'react';
import { useAuth } from 'context/AuthProvider/useAuth';

interface IRota {
  label: string;
  to: string;
}

export default function Navbar() {

  const auth = useAuth();
  const [rotas2, setRotas2] = useState<IRota[]>([]);

  const rotas = [{
    label: 'Home',
    to: '/'
  },
  {
    label: 'Receitas',
    to: '/receitas'
  },
  {
    label: 'Receita',
    to: '/receita/nova'
  }
  ];






  useEffect(() => {
    if (auth.login) {
      const rota: IRota[] = [];
      setRotas2(rota);
    } else {
      const rota = [{
        label: 'Login',
        to: '/login'
      },
      {
        label: 'Cadastro',
        to: '/cadastro'
      }];
      setRotas2(rota);
    }
  }, []);



  return (
    <nav className={styles.nav}>
      <img src={logo} />
      <ul className={styles.nav__list}>
        {rotas.map((rota, index) => (
          <li key={index} className={styles.nav__link}>
            <Link to={rota.to}>
              {rota.label}
            </Link>
          </li>
        ))}
        {rotas2.map((rota, index) => (
          <li key={index} className={styles.sidebutton}>
            <Link to={rota.to}>
              {rota.label}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}