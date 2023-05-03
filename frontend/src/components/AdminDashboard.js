import React from 'react';
import {AppBar, Button, Toolbar} from "@mui/material";
import {Link} from "react-router-dom";

function AdminDashboard() {

	return (
		<AppBar>
			<Toolbar>
				<Button component={Link} to="/addproduct" color="inherit">
					Add Product
				</Button>
				<div style={{flexGrow: 1}}/>
				<Button color="inherit" component={Link} to="/">
					Logout
				</Button>
			</Toolbar>
		</AppBar>
	);
}

export default AdminDashboard;