import React, {useState} from 'react';
import '../css/RegistrationForm.css';
import {TextareaAutosize, TextField} from "@mui/material";
import axios from "axios";
import {useNavigate} from "react-router-dom";

function RegistrationForm() {
	const navigate = useNavigate()
	const [name, setName] = useState('');
	const [mobileNumber, setMobileNumber] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [address, setAddress] = useState('');
	const [error, setError] = useState('');

	const handleNameChange = (event) => {
		setName(event.target.value);
	};
	const handleMobileNumberChange = (event) => {
		setMobileNumber(event.target.value);
	};
	const handleEmailChange = (event) => {
		setEmail(event.target.value);
	};
	const handlePasswordChange = (event) => {
		setPassword(event.target.value);
	};
	const handleAddressChange = (event) => {
		setAddress(event.target.value);
	};
	const handleSubmit = async (event) => {
		event.preventDefault();
		if (name.trim().length === 0) {
			alert("Name field can't be empty.");
			return;
		}
		if (mobileNumber.trim().length === 0) {
			alert("Contact field can't be empty.");
			return;
		}
		if (email.trim().length === 0) {
			alert("Email field can't be empty.");
			return;
		}
		if (password.trim().length === 0) {
			alert("Password field can't be empty.");
			return;
		}
		try {
			const response = await axios.post('http://localhost:1111/api/users/create', {
				name: name,
				mobileNumber: mobileNumber,
				email: email,
				password: password,
				address: address,
				role: "CUSTOMER"
			});
			console.log(response.data);
			if (response.data === "Email already Exist.") {
				alert(response.data);
			} else {
				// alert(response.data.name);
				// navigate("/", {state: {data: response.data}});
				navigate(-1);
			}
		} catch (error) {
			console.log(error);
			setError(error.response);
		}
	};

	return (
		<form onSubmit={handleSubmit}>
			<div className="registerform">
				<label>Register</label>
				<TextField variant="outlined" label="Name" value={name} onChange={handleNameChange}/>
				<TextField variant="outlined" label="Email" type="email" value={email} onChange={handleEmailChange}/>
				<TextField variant="outlined" label="Password" type="password" value={password} onChange={handlePasswordChange}/>
				<TextField variant="outlined" label="Mobile Number" type="mobilenumber" value={mobileNumber} onChange={handleMobileNumberChange}/>
				<div className="address-field">
					<TextareaAutosize
						value={address}
						onChange={handleAddressChange}
						minRows={5}
						maxRows={8}
						maxLength={100}
						style={{width: 295, fontFamily: 'Arial, sans-serif'}}
						placeholder=" Address "
					/>
				</div>
				<button type="submit">Submit</button>
			</div>
		</form>
	);
}

export default RegistrationForm;
