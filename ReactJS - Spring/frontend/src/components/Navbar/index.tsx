import logo from 'assets/logo.png';
import { Link } from 'react-router-dom';
import styles from './Navbar.module.scss';


export default function Navbar() {
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
    to: '/receita'
  }
  ];
  const rotas2 = [{
    label: 'Login',
    to: '/u/login'
  },
  {
    label: 'Registrar-se',
    to: '/u/cadastrar'
  }];

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