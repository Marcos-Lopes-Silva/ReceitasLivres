import { useAuth } from 'context/AuthProvider/useAuth';
import { useEffect } from 'react';
import { Navigate, useLocation } from 'react-router-dom';


export default function Logout() {
    const location = useLocation();

    const auth = useAuth();

    
    useEffect(() => {
        auth.logout();
    }, []);
    
    return <Navigate to='/' state={{ from: location }} replace />;
    
}