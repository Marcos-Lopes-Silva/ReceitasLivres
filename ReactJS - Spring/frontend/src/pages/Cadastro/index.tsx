import { useForm } from 'react-hook-form';
import styles from './Cadastro.module.scss';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { FormEvent, useState } from 'react';
import { AWSBucket, Request } from 'context/AuthProvider/utils';


const createEnderecoFormSchema = z.object({
  cep: z.string().min(9, 'O CEP precisa ter 8 caracteres.').max(9, 'O CEP precisa ter 8 caracteres.'),
  logradouro: z.string().min(1, 'O logradouro é obrigatório.').max(100),
  bairro: z.string().min(1, 'O bairro é obrigatório.').max(100),
  uf: z.string().min(1, 'O UF é obrigatório.').max(100),
  cidade: z.string().min(1, 'A cidade é obrigatória.').max(100),
  complemento: z.string()
})


const createUserFormSchema = z.object({
  nome: z.string().min(1, 'O nome é obrigatório.').max(100),
  login: z.string().email('Formato de email inválido.').min(1, 'O email é obrigatório'),
  senha: z.string().min(6, 'A senha precisa de no mínimo 6 caracteres.').max(100),
  dataNascimento: z.coerce.date(),
  photo: z.instanceof(FileList).transform(fileList => fileList.item(0)),
  photoId: z.string().optional(),
  endereco: createEnderecoFormSchema
})



type UserFormSchema = z.infer<typeof createUserFormSchema>;

export default function Cadastro() {
  const [ imagePreview, setImagePreview ] = useState<string | null>(null);
  const { register, handleSubmit, setValue, setFocus, formState: { errors} } = useForm<UserFormSchema>({
    resolver: zodResolver(createUserFormSchema),
  });

  
  
  const checkCEP = (e: FormEvent<HTMLInputElement>) => {
    const target = e.target as typeof e.target & {
      value: string;
    }

    if (target.value) {
      const cep = target.value.replace(/\D/g, '');
      fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(res => res.json())
        .then(data => {
          setValue('endereco.logradouro', data.logradouro);
          setValue('endereco.bairro', data.bairro);
          setValue('endereco.cidade', data.localidade);
          setValue('endereco.uf', data.uf);
          setValue('endereco.cep', data.cep);
          setFocus('endereco.complemento');
        })
        .catch((err) => { console.log(err); });
    }
  };

  function handleImageChange(e: FormEvent<HTMLInputElement>) {
    e.preventDefault();
    const target = e.target as typeof e.target & {
      files: FileList;
    }
    const file = target.files.item(0);

    if(file) {
      const render = new FileReader();
      render.onload = () => {
        setImagePreview(render.result as string);
      }

      render.readAsDataURL(file);
    }
  }


  async function createUser(data: UserFormSchema) {
    
    
    const responseImage = await Request(
      '/upload/image',
      'post',
      '',
      {
        fileName: data.photo?.name,
        contentLength: data.photo?.size,
        contentType: data.photo?.type
      });

    setValue('photoId', responseImage.id);

    const request = await AWSBucket(responseImage.url, data.photo);
    console.log(request);


    const responseUser = await Request('user/create', 'post', '', data);
    console.log(responseUser);  
  }

  return (
    <form className={styles.cadastro} onSubmit={handleSubmit(createUser)}>
      <div className={styles.cadastro__formssection}>
        <fieldset id='pessoais' className={styles.cadastro__formssection__fieldset}>
          <p className={styles.cadastro__formssection__a}>Dados Pessoais</p>
          <div className={styles.cadastro__formssection__div}>
            <p>Nome</p>
            <input type="text" {...register('nome')} />
            {errors.nome && <span className={styles.cadastro__formssection__error}>{errors.nome.message}</span>}
          </div>
          <div className={styles.cadastro__formssection__div}>
            <p>Email</p>
            <input type="email" {...register('login')} />
            {errors.login && <span className={styles.cadastro__formssection__error}>{errors.login.message}</span>}
          </div>
          <div className={styles.cadastro__formssection__div}>
            <p>Senha</p>
            <input type="password" {...register('senha')} />
            {errors.senha && <span className={styles.cadastro__formssection__error}>{errors.senha.message}</span>}
          </div>
          <div className={styles.cadastro__formssection__div}>
            <p>Data de Nascimento</p>
            <input className={styles.cadastro__formssection__date} type="date" id='datePicker' {...register('dataNascimento')} />
            {errors.dataNascimento && <span className={styles.cadastro__formssection__error}>{errors.dataNascimento.message}</span>}
          </div>
          <div className={styles.cadastro__formssection__div}>
            <p>Avatar</p>
            <input type='file' className={styles.cadastro__formssection__imagem} {...register('photo', {onChange: handleImageChange})}  accept='image/*' />
            { imagePreview && <img src={imagePreview} alt='Avatar' style={{ transition:'2s', maxWidth: '100%', maxHeight: '200px', marginTop: '20px', borderRadius:'10px' }}/> }
          </div>
        </fieldset>

        <fieldset id='endereco' className={styles.cadastro__formssection__fieldset}>

          <p className={styles.cadastro__formssection__a}>Endereço</p>

          <div className={styles.cadastro__formssection__div}>
            <p>CEP</p>
            <input type="text" {...register('endereco.cep')} onBlur={checkCEP} />
            {errors.endereco?.cep && <span className={styles.cadastro__formssection__error}>{errors.endereco.cep.message}</span>}
          </div>

          <div className={styles.cadastro__formssection__div}>
            <p>Logradouro</p>
            <input type="text" {...register('endereco.logradouro')} />
            {errors.endereco?.logradouro && <span className={styles.cadastro__formssection__error}>{errors.endereco.logradouro.message}</span>}
          </div>

          <div className={styles.cadastro__formssection__div}>
            <p>Bairro</p>
            <input type="text" {...register('endereco.bairro')} />
            {errors.endereco?.bairro && <span className={styles.cadastro__formssection__error}>{errors.endereco.bairro.message}</span>}
          </div>

          <div className={styles.cadastro__formssection__div}>
            <p>UF</p>
            <input type="text" {...register('endereco.uf')} />
            {errors.endereco?.uf && <span className={styles.cadastro__formssection__error}>{errors.endereco.uf.message}</span>}
          </div>

          <div className={styles.cadastro__formssection__div}>
            <p>Cidade</p>
            <input type="text" {...register('endereco.cidade')} />
            {errors.endereco?.cidade && <span className={styles.cadastro__formssection__error}>{errors.endereco.cidade.message}</span>}
          </div>

          <div className={styles.cadastro__formssection__div}>
            <p>Complemento</p>
            <input type="text" {...register('endereco.complemento')} />
            {errors.endereco?.complemento && <span className={styles.cadastro__formssection__error}>{errors.endereco.complemento.message}</span>}
          </div>

        </fieldset>
      </div>
      <input type="submit" value="Cadastrar" className={styles.button} id="button" />

    </form>
  );
}