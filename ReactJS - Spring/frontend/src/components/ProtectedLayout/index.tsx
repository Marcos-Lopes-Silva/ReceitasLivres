import { useAuth } from 'context/AuthProvider/useAuth';
import { Navigate, useLocation } from 'react-router-dom';

export const ProtectedLayout = ({ children }: { children: JSX.Element }) => {
    const auth = useAuth();
    const location = useLocation();

    if (!auth.login) {
        return <Navigate to='/login' state={{ from: location }} replace />;
    }

    return children;
};