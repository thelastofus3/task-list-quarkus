import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.scss'
import {Router} from "./components/route/Router.jsx";

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Router />
  </StrictMode>,
)
