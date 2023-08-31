import 'normalize.css';
import React from 'react';
import * as ReactDOMClient from 'react-dom/client';
import './index.css';
import Router from './routes';

const container = document.getElementById('root');

if(!container) 
  throw new Error('Container not found');


const root = ReactDOMClient.createRoot(container)

root.render(
  <React.StrictMode>
    <Router />
  </React.StrictMode>
)