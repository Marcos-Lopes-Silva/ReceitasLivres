import { useAuth } from 'context/AuthProvider/useAuth';
import { Navigate, Outlet, useLocation } from 'react-router-dom';

export const ProtectedLayout = () => {
    const auth = useAuth();
    const location = useLocation();

    

    return(
        auth?.login 
        ? <Outlet/>
        : <Navigate to='/login' state={{ from: location }} replace />
    )   
};