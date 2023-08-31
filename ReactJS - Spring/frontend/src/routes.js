import Footer from 'components/Footer';
import Navbar from 'components/Navbar';
import PaginaPadrao from 'components/PaginaPadrao';
import { AuthProvider } from 'context/AuthProvider';
import Cadastro from 'pages/Cadastro';
import Home from 'pages/Home';
import Login from 'pages/Login';
import NotFound from 'pages/NotFound';
import NovaReceita from 'pages/NovaReceita';
import Receita from 'pages/Receita';
import Receitas from 'pages/Receitas';
import Logout from 'components/Logout';
import { ProtectedLayout } from 'components/ProtectedLayout';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


export default function AppRouter() {
  return (
    <main className='container'>
      <Router>
        <AuthProvider>
          <Navbar />
          <Routes>
            <Route path='/' element={<PaginaPadrao />} >
              <Route index element={<Home />} />
              <Route path='receitas' element={<Receitas />} />
              <Route path='receita/:id' element={<Receita />} />
              <Route element={<ProtectedLayout />}>
                  <Route path='/novareceita' element={<NovaReceita/>}/>
              </Route> 
            
            </Route>
            <Route path='/'>
              <Route path='cadastrar' element={<Cadastro />} />
              <Route path='login' element={<Login />} />
              <Route path='logout' element={<Logout/>} />
            </Route>
            <Route path='*' element={<NotFound />} />
          </Routes>
          <Footer />
        </AuthProvider>
      </Router>
    </main>
  )
}