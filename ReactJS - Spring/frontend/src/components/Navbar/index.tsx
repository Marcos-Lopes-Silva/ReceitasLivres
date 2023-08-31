import logo from 'assets/logo.png';
import { Link } from 'react-router-dom';
import styles from './Navbar.module.scss';
import { useEffect, useState } from 'react';
import { CgUser } from 'react-icons/cg';
import { useAuth } from 'context/AuthProvider/useAuth';
import Sidebar from './Sidebar';

interface IRota {
  label: string;
  to: string;
}

export default function Navbar() {

  const auth = useAuth();
  const [sidebar, setSidebar] = useState(false);
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
    to: '/novareceita'
  }
  ];


  const showSidebar = () => setSidebar(!sidebar);



  useEffect(() => {
    if (auth.login != (undefined || null) || '') {
      const rota: IRota[] = []

      setRotas2(rota);
    }else {
      const rota = [{
        label: 'Login',
        to: '/login'
      },
      {
        label: 'Cadastrar-se',
        to: '/cadastrar'
      }];
      setRotas2(rota);
    }
  }, [auth.login]);



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
        
      </ul>
      <ul className={styles.nav__list2}>


        {rotas2.length > 0 
        ? rotas2.map((rota: IRota, index: number) => (
            <li key={index} className={styles.nav__sidebutton}>
              <Link to={rota.to}>
                {rota.label}
              </Link>
            </li>
          ))
          : <CgUser size={50} onClick={showSidebar} className={styles.nav__sidebutton}/>}
            {sidebar && <Sidebar active={setSidebar}/>}
      </ul>
    </nav>
  );
}