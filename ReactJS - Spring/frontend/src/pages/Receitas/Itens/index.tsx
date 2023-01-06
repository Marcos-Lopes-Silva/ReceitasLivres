import Item from './Item'
import itens from './itens.json'
import styles from './Itens.module.scss'
export default function Itens() {
    return (
        <div className={styles.itens}>
            {itens.map((item) => (
                <Item
                    id={item.id}
                    title={item.title}
                    description={item.description}
                    photo={item.photo}
                    size={item.size}
                    serving={item.serving}
                    category={
                        item.category
                    }
                    price={item.price}
                />
            ))}
        </div>
    )
}