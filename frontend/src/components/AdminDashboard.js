import {
	AppBar,
	Button,
	Paper,
	Table,
	TableBody,
	TableCell,
	TableContainer,
	TableHead,
	TableRow,
	Toolbar
} from "@mui/material";
import {Link, useLocation} from "react-router-dom";
import React, {useState} from 'react';
import '../css/AdminDashboard.css'
import axios from "axios";

function AdminDashboard() {
    // const location = useLocation();
    // const id = location.state.data.id;
    // const name = location.state.data.name;
    // const email = location.state.data.email;
    // const mobileNumber = location.state.data.mobileNumber;
    // const role = location.state.data.role;
	const [products, setProducts] = useState([]);
    const fetchProducts = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.get('http://localhost:1111/api/product/getProducts');
            console.log(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <>
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
			<div>
				<TableContainer component={Paper} variant="outlined">
					<Table aria-label="demo table">
						<TableHead>
							<TableRow>
								<TableCell>Dessert</TableCell>
								<TableCell>Calories</TableCell>
								<TableCell align="right">Actions</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							<TableRow>
								<TableCell>Frozen yoghurt</TableCell>
								<TableCell>109</TableCell>
								<TableCell align="right">
									<IconButton>
										<EditIcon />
									</IconButton>
									<IconButton>
										<DeleteIcon />
									</IconButton>
								</TableCell>
							</TableRow>
							<TableRow>
								<TableCell>Cupcake</TableCell>
								<TableCell>305</TableCell>
								<TableCell align="right">
									<IconButton>
										<EditIcon />
									</IconButton>
									<IconButton>
										<DeleteIcon />
									</IconButton>
								</TableCell>
							</TableRow>
						</TableBody>
					</Table>
				</TableContainer>
			</div>
		</>
    );
}

export default AdminDashboard;