import './App.css';
import LoginPage from './components/LoginPage';
import React from "react";
import {Route, Routes} from "react-router-dom";
import NoMatch from "./components/NoMatch";
import RegistrationForm from "./components/RegistrationForm";
import AdminDashboard from "./components/AdminDashboard";
import AddProduct from "./components/addProduct";
import EditProduct from "./components/EditProduct";
import CustomerDashboard from "./components/CustomerDashboard";

function App() {
	return (
		<Routes>
			<Route path="/" element={<LoginPage/>}></Route>
			<Route path="/register" element={<RegistrationForm/>}></Route>
			<Route path="/admindashboard" element={<AdminDashboard/>}></Route>
			<Route path="/addproduct" element={<AddProduct/>}></Route>
			<Route path="/editproduct/:id" element={<EditProduct/>}></Route>
			<Route path="/customerdashboard" element={<CustomerDashboard/>}></Route>
			<Route path="*" element={<NoMatch/>}></Route>
		</Routes>
	);
}

export default App;
