import "normalize.css";
import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Receitas from './pages/Receitas';



const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <Receitas />
  </React.StrictMode>
);