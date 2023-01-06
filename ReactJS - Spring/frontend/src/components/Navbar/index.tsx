import logo from "assets/logo.png"
import Container from "components/Container"
import styles from "./Navbar.module.scss"

interface IProps {
    children?: React.ReactNode
}

export default function Navbar(props: IProps) {
    return (
        <nav className={styles.nav}>

            <Container>
                <ul className={styles.lista}>
                    <li className={styles.item}><img src={logo} alt="logo" /></li>
                    <li className={styles.item}>
                        Home
                    </li>
                    <li className={styles.itemLogin}>
                        Receitas
                    </li>

                    <li className={styles.login}>
                        Login
                    </li>
                    <li className={styles.sign}>
                        Registrar-se
                    </li>
                </ul>
            </Container>
            {props.children}
        </nav>
    )
}