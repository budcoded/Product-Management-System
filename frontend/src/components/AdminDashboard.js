import React, {useState} from 'react';
import '../css/AdminDashboard.css'
import {useLocation} from "react-router-dom";

function AdminDashboard () {
	const location = useLocation();
	const id = location.state.data.id;
	const name = location.state.data.name;
	const email = location.state.data.email;
	const mobileNumber = location.state.data.mobileNumber;
	const role = location.state.data.role;
	return (
		<div>
			<h2>This is admin dashboard.</h2>
			<h3>Data Received: </h3>
			<p>{id}, {name}, {email}, {mobileNumber}, {role}</p>
		</div>
	);
}

export default AdminDashboard;