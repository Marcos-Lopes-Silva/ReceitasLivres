import { useForm } from 'react-hook-form';
import styles from './Cadastro.module.scss';


export default function Cadastro() {

  const { register, setValue, setFocus } = useForm();
  // eslint-disable-next-line
  const checkCEP = (e: any) => {
    if (e.target.value) {
      const cep = e.target.value.replace(/\D/g, '');
      console.log(cep);
      fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(res => res.json())
        .then(data => {
          console.log(data);
          setValue('logradouro', data.logradouro);
          setValue('bairro', data.bairro);
          setValue('cidade', data.localidade);
          setValue('uf', data.uf);
          setValue('cep', data.cep);
          setFocus('complemento');

        }).catch((err) => { console.log(err); });
    }
  };

  return (
    <form className={styles.cadastro}>
      <fieldset id='pessoais' className={styles.cadastro__fieldset}>
        <p className={styles.cadastro__a}>Dados Pessoais</p>
        <div className={styles.cadastro__div}>
          <p>Nome</p>
          <input type="text" />
        </div>
        <div className={styles.cadastro__div}>
          <p>Email</p>
          <input type="email" />
        </div>
        <div className={styles.cadastro__div}>
          <p>Senha</p>
          <input type="password" />
        </div>
        <div className={styles.cadastro__div}>
          <p>Data de Nascimento</p>
          <input className={styles.cadastro__date} type="date" id='datePicker' />
        </div>
      </fieldset>
      <fieldset id='endereco' className={styles.cadastro__fieldset}>
        <p className={styles.cadastro__a}>Endereço</p>
        <div className={styles.cadastro__div}>
          <p>Logradouro</p>
          <input type="text" {...register('logradouro')} />
        </div>
        <div className={styles.cadastro__div}>
          <p>Bairro</p>
          <input type="text" {...register('bairro')} />
        </div>
        <div className={styles.cadastro__div}>
          <p>CEP</p>
          <input type="text" {...register('cep')} onBlur={checkCEP} />
        </div>
        <div className={styles.cadastro__div}>
          <p>UF</p>
          <input type="text" {...register('uf')} />
        </div>
        <div className={styles.cadastro__div}>
          <p>Cidade</p>
          <input type="text" {...register('cidade')} />
        </div>
        <div className={styles.cadastro__div}>
          <p>Complemento</p>
          <input type="text" {...register('complemento')} />
        </div>

      </fieldset>



      <input type="button" value="Cadastrar" className={styles.button} id="button" />
    </form>

  );
}