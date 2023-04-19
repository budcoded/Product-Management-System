import React, { useState } from 'react';
import '../css/register.css';
// import {TextField} from "@mui/material";
import {TextareaAutosize, TextField} from "@mui/material";

function RegistrationForm() {
	const [name, setName] = useState('');
	const [mobileNumber, setMobileNumber] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [address, setAddress] = useState('');

	const handleSubmit = (event) => {
		event.preventDefault();
	};
	const handleChange = (event) => {
		setAddress(event.target.value);
	};

	return (
		<form onSubmit={handleSubmit}>
			<div className="registerform">
				<label>Register</label>
				<TextField variant="outlined" label="Name" />
				<TextField variant="outlined" label="Email" type="email" />
				<TextField variant="outlined" label="Password" type="password" />
				<TextField variant="outlined" label="Mobile Number" type="mobilenumber" />
				<div className="address-field">
					<TextareaAutosize
						value={address}
						onChange={handleChange}
						minRows={5}
						maxRows={8}
						maxLength={100}
						style={{ width: 295,fontFamily: 'Arial, sans-serif' }}
						placeholder=" Address "
					/>
				</div>
				<button type="submit">Submit</button>
			</div>
		</form>
	);
}

export default RegistrationForm;
