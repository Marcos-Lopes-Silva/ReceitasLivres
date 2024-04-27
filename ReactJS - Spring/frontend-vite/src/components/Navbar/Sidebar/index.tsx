import styles from './Sidebar.module.scss';
import { Link } from 'react-router-dom';
import { CgClose } from 'react-icons/cg'


interface IProps {
    active: any;
}

export default function Sidebar({ active }: IProps) {

    const closeSidebar = () => {
        active(false);
    }

    const rotas = [{
        label: 'Perfil',
        to: '/profile/:id'
    },
    {
      label: 'Sair',
      to: '/logout'  
    }]

    return(
        active ? (
            <div className={styles.sidebar}>
                    <ul className={styles.sidebar__list}>
                    
                        {rotas.map((rota, index) => (
                        <li key={index} className={styles.sidebar__link}>
                            <Link to={rota.to} onClick={closeSidebar}>
                                {rota.label}
                            </Link>
                        </li>
                        ))}
                        <hr />
                        <li className={styles.sidebar__close} >
                            <CgClose size={35} onClick={closeSidebar} />
                        </li>
                       
                    </ul>
            </div>
        )
        : null
    )
}