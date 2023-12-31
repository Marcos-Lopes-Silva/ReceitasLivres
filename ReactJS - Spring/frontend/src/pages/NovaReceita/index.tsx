import { useState } from 'react';
import styles from './NovaReceita.module.scss';
import { MdCircle } from 'react-icons/md';
import Filtros from 'components/Filtros';
import { useFieldArray, useForm } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { AWSBucket, Request } from 'context/AuthProvider/utils';

const createReceitaFormSchema = z.object({
    titulo: z.string()
        .min(1, 'O titulo é obrigatório.')
        .transform(name => {
            return name.trim().split(' ').map(word => {
                return word[0].toUpperCase() + word.slice(1)
            })
                .join(' ')
        }),
    modoPreparo: z.string()
        .min(1, 'O modo de preparo é obrigatório.'),
    categoria: z.string()
        .min(1, 'A categoria é obrigatória.'),
    ingredientes: z.array(
        z.object({
            name: z.string()
                .min(1, 'O ingrediente é obrigatório.'),
        }))
        .min(1, 'Insira pelo menos um ingredientes.'),
    size: z.coerce.number().min(5, 'A porção precisa ser maior que 5g.'),
    descricao: z.string().min(1, 'A descrição é obrigatória.'),
    serve: z.coerce.number().min(1, 'A receita precisa servir pelo menos uma pessoa.'),
    photo: z.instanceof(FileList).transform((fileList) => fileList.item(0)),
})

type ReceitaFormSchema = z.infer<typeof createReceitaFormSchema>;

export default function NovaReceita() {

    const { register, handleSubmit, control, formState: { errors, isSubmitting } } = useForm<ReceitaFormSchema>({
        resolver: zodResolver(createReceitaFormSchema),
    });
    const { fields, append, remove } = useFieldArray({
        control,
        name: 'ingredientes',
    });
    const [filtro, setFiltro] = useState<number | null>(null);


    function addNewIngrediente() {
        append({ name: '' });
    }

    async function submitReceita(data: ReceitaFormSchema) {
        const responseImage = await Request(
            'upload/image',
            'post',
            '',
            {
                fileName: data.photo?.name,
                contentLength: data.photo?.size,
                contentType: data.photo?.type
            });

        await AWSBucket(responseImage.uploadSignedUrl, data.photo, responseImage.contentType);

        const responseReceita = await Request(
            'receita/create',
            'post',
            '',
            {
                ...data, photoId: responseImage.fileReferenceId
            });
    }




    return (
        <form className={styles.forms} onSubmit={handleSubmit(submitReceita)}>
            <h2>Nos envie sua receita!</h2>
            <hr />
            <div>
                <div className={styles.forms__inputbox}>
                    <label>Titulo</label>
                    <input type='text' {...register('titulo')} />
                    {errors.titulo && <span className={styles.forms__error}>{errors.titulo.message}</span>}
                </div>
                <div className={styles.forms__inputbox}>
                    <label>Modo de Preparo</label>
                    <textarea {...register('modoPreparo')} />
                    {errors.modoPreparo && <span className={styles.forms__error}>{errors.modoPreparo.message}</span>}
                </div>
                <div className={styles.forms__inputlist}>
                    <label>
                        Ingredientes
                        <button className={styles.forms__inputList__appendbtt} type='button' onClick={addNewIngrediente}>Adicionar</button>
                    </label>
                    {fields.map((field, index) => (
                        <div key={field.id} >
                            <div className={styles.forms__inputList__ingrediente}>
                                <input type='text' {...register(`ingredientes.${index}.name`)} />
                                <button
                                    type='button'
                                    onClick={() => remove(index)}
                                    className={styles.forms__inputList__removebtt}
                                >
                                    <MdCircle size={14} />
                                </button>
                            </div>

                        </div>
                    ))}
                    {errors.ingredientes && <span className={styles.forms__error}>{errors.ingredientes.message}</span>}
                </div>
                <div className={styles.forms__inputbox}>
                    <label>Porção (em gramas)</label>
                    <input type='text' {...register('size')} />
                </div>
                <div className={styles.forms__inputbox}>
                    <label>Imagem da Receita</label>
                    <div>
                        <input type='file' name='image' />
                    </div>

                </div>
                <div className={styles.forms__inputbox}>
                    <label>Descrição</label>
                    <input type='text' {...register('descricao')} />
                </div>
                <div className={styles.forms__categorias}>
                    <label>
                        Categorias
                    </label>
                    <Filtros filtro={filtro} setFiltro={setFiltro} />
                </div>
                <div className={styles.forms__inputbox}>
                    <label>Servem <input type='text' {...register('serve')} /> pessoas.</label>
                </div>
                <input className={styles.forms__button} type='submit' value='Enviar' />
            </div>
        </form>
    );
}