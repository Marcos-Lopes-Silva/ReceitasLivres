import { SyntheticEvent, useState } from 'react';
import styles from './NovaReceita.module.scss';
import { ISetReceita } from 'types/types';
import { setReceita } from 'context/AuthProvider/utils';
import Filtros from 'components/Filtros';


export default function NovaReceita() {


    const [ingredientesList, setIngredientes] = useState([{ingrediente: ''}]);
    const [filtro, setFiltro] = useState<number | null>(null);
    let receita: ISetReceita;

    console.log(ingredientesList);
    const handleIngredienteAdd = () => {
        setIngredientes([...ingredientesList, { ingrediente: ''}])
    };

    const handleIngredienteRm = (index: number) => {
        const list = [...ingredientesList];
        list.splice(index, 1);
        setIngredientes(list);
    };

    const handleIngredienteChange = (e: SyntheticEvent, index: number) => {
        console.log(index);
        const { name, value } = e.target as typeof e.target & {
            name: {value: ''} ,
            value: { ingrediente: '' }
        };
        const list = [...ingredientesList];
        list[index] = value;
        setIngredientes(list);

    }


    const forms = (e: SyntheticEvent) => {
        console.log('teste');
        e.preventDefault();
        const ingredientes = ingredientesList.toString();

        const target = e.target as typeof e.target & ISetReceita;

        
        
        receita.titulo = target.titulo;
        receita.categoria = target.categoria;
        receita.ingredientes = ingredientes;
        receita.modoPreparo = target.modoPreparo;
        receita.size = target.size;
        


        console.log(`Olá ${ingredientes}`);
        //setReceita(receita);
    };

    


    return (
       <form className={styles.forms} onSubmit={forms}>
        <h2>Nos envie sua receita!</h2>
        <hr />
        <div>
            
            <div className={styles.forms__inputbox}>
                <label>Titulo</label> 
                <input type="text" name="titulo" required/>
            </div>
            <div className={styles.forms__inputbox}>
                <label>Modo de Preparo</label>
                <textarea name="modoPreparo" required/>
            </div>
            <div className={styles.forms__inputlist}>
                <label>Ingredientes</label>
                {ingredientesList.map((value, key) => (
                <div key = {key} >
                    
                    <div className={styles.forms__column}>
                        <input type='text' name='ingredientes' required value={value.ingrediente} onChange={(e) => handleIngredienteChange(e, key)}/>
                        {ingredientesList.length - 1 === key && ingredientesList.length < 20 && (
                            <button type='button' className={styles.forms__addButton} onClick={handleIngredienteAdd}>+</button>
                        )}
                        
                    </div>
                    <div className={styles.forms__inputlist}>
                        {ingredientesList.length > 1 &&  <button type='button' className={styles.forms__removeButton} onClick={() => handleIngredienteRm(key)}>Remover</button>}
                    
                    </div>
                </div>
                ))}
            </div>
            <div className={styles.forms__inputbox}>
                <label>Porção (em gramas)</label>
                <input type="text" name="size"/>
            </div>
            <div className={styles.forms__inputbox}>
                <label>Imagem da Receita</label>
                <div>
                <input type="file" name="image"/> 
                </div>
                
            </div>
            <div className={styles.forms__inputbox}>
                <label>Descrição</label>
                <input type='text' name="descricao"></input>
            </div>
            <div className={styles.forms__categorias}>
                <label>Categorias</label> 
                <Filtros filtro={filtro} setFiltro={setFiltro}/>
            </div>
            <div className={styles.forms__inputbox}>
                <label>Servem <input type="number" /> pessoas.</label> 
            </div>
            <input className={styles.forms__button} type="submit" value="Enviar"/>
        </div>
       </form>
    );
}