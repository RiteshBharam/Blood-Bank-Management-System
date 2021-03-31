import React from 'react';
import { Navigate } from 'react-router-dom';
import DashboardLayout from 'src/layouts/DashboardLayout';
import MainLayout from 'src/layouts/MainLayout';
import DashboardView from 'src/views/reports/DashboardView';
import NotFoundView from 'src/views/errors/NotFoundView';
import LoginView from 'src/views/auth/LoginView';
import RegisterView from 'src/views/auth/RegisterView';
import Requests from 'src/views/Requests/requests';
import History from 'src/views/History/history';
import DonorForm from 'src/views/Donor/DonorForm';
import DonorDetails from 'src/views/Donor/DonorDetails';
import SeekerForm from "src/views/Seeker/seekerForm";
import HomePage from 'src/views/Home/home';
import DonorLoginView from './views/auth/DonorLogin';
import DonateButton from './views/Donor/Donate';

const routes = [
  {
    path: 'app',
    element: <DashboardLayout />,
    children: [

      { path: 'dashboard', element: <DashboardView /> },
      { path: 'requests', element: <Requests /> },
      { path: 'history', element: <History /> },
      { path: 'donor', element: <DonorDetails /> },
      { path: '*', element: <Navigate to="/404" /> },

    ]
  },
  {
    path: '/',
    element: <MainLayout />,
    children: [

      { path: '404', element: <NotFoundView /> },
      { path: '/', element: <Navigate to="/home" /> },
      { path: 'login', element: <LoginView /> },
      { path: 'donorlogin', element: <DonorLoginView /> },
      { path: 'home', element: <HomePage /> },
      { path: 'register', element: <RegisterView /> },
      { path: 'donorform', element: <DonorForm /> },
      { path: 'donate', element: <DonateButton /> },
      { path: 'seekerform', element: <SeekerForm /> },
      { path: '*', element: <Navigate to="/404" /> }
    ]
  }
];

export default routes;