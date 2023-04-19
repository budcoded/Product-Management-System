// import './App.css';
// import React from 'react';
// import LoginPage from './components/login';
// import RegistrationForm from "./components/register";
// import AddProduct from "./components/addProduct";
// import adminDashboard from "./components/adminDashboard";
// function App() {
// 	return (
// 		// <LoginPage/>
// 		//<RegistrationForm/>
// 		//<AddProduct/>
// 		<adminDashboard/>
// 	);
// }
//
// export default App;

import React from 'react';
import {BrowserRouter, BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import LoginPage from './components/login';
import RegistrationForm from "./components/register";
import AddProduct from "./components/addProduct";
import adminDashboard from "./components/adminDashboard";

function App() {
	return (
		<Routes>
				<Route path="/" component={<LoginPage/>} />
				{/*<Route path="/login" component={LoginPage} />*/}
				<Route path="/register" component={<RegistrationForm/>} />
				<Route path="/addProduct" component={<AddProduct/>} />
		</Routes>
	);
}

export default App;
