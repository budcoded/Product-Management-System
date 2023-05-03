import React, {useState} from 'react';
import axios from 'axios';
import '../css/LoginForm.css';
import {Button, TextField} from "@mui/material";
import {useNavigate} from "react-router-dom";

function LoginPage() {
	const navigate = useNavigate()
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [error, setError] = useState('');

	const handleEmailChange = (event) => {
		setEmail(event.target.value);
	};

	const handlePasswordChange = (event) => {
		setPassword(event.target.value);
	};

	const handleSubmit = async (event) => {
		event.preventDefault();
		if (email.trim().length === 0) {
			alert("Email field can't be empty.");
			return;
		}
		if (password.trim().length === 0) {
			alert("Password field can't be empty");
			return;
		}
		try {
			const response = await axios.post('http://localhost:1111/api/users/login', {
				email: email,
				password: password,
			});
			console.log(response);
		} catch (error) {
			console.log(error);
			setError(error.response);
		}
	};

	const handleRegister = () => {
		navigate("/register")
	}

	return (
		<div>
			<h1 className="h1"> Login Page </h1>
			<form className="login-form" onSubmit={handleSubmit}>
				<div className="input-box">
					<TextField variant="outlined" value={email} id="email" onChange={handleEmailChange} label="Email"
							   type="email"/>
				</div>
				<div className="input-box">
					<TextField variant="outlined" value={password} id="password" onChange={handlePasswordChange}
							   label="Password" type="password"/>
				</div>
				<div className="button-container">
					<Button variant="contained" type="submit">Login</Button>
					<Button variant="text" onClick={handleRegister}>Create Account</Button>
				</div>
			</form>
			{error && <p>{error}</p>}
		</div>
	);
}

export default LoginPage;