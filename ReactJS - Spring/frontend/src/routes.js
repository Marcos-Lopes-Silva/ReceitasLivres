import Footer from 'components/Footer';
import Navbar from 'components/Navbar';
import PaginaPadrao from 'components/PaginaPadrao';
import Cadastro from 'pages/Cadastro';
import Home from 'pages/Home';
import Login from 'pages/Login';
import NotFound from 'pages/NotFound';
import Receita from 'pages/Receita';
import Receitas from 'pages/Receitas';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


export default function AppRouter() {
  return (
    <main className='container'>
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' element={<PaginaPadrao />} >
            <Route index element={<Home />} />
            <Route path='receitas' element={<Receitas />} />
            <Route path='login' element={<Login />} />
            <Route path='receita' element={<Receita />} />
          </Route>
          <Route path='/u'>
            <Route path='cadastrar' element={<Cadastro />} />
          </Route>
          <Route path='*' element={<NotFound />} />
        </Routes>
        <Footer />
      </Router>
    </main>
  );
}