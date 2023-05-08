
import React from 'react';
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material';
import {useLocation} from "react-router-dom";
import axios from "axios";

async function sendOrderToBackend(cart) {
	// const response = await fetch('http://localhost:1111/api/order/addOrder', {
	// 	method: 'POST',
	// 	headers: {
	// 		'Content-Type': 'application/json'
	// 	},
	// 	body: JSON.stringify({ cart })
	// });
	const response = await axios.post('http://localhost:1111/api/order/addOrder', {})
	if(response.data === "Order Successfully Added.")
		console.log("Added successfully at backend");
	else{
		throw new Error('Failed to send order to backend');
	}
}

function CartSummary() {
	// Calculate the total price of the items in the cart
	const location = useLocation();
	const cart = location.state;
	console.log(cart);
	if (!cart || !Array.isArray(cart)) {
		return <div>No items in cart.</div>;
	}
	const totalPrice = cart.reduce((total, item) => total + item.price, 0);

	const handleCheckout = async () => {
		try {
			console.log("At cart summary");
			await sendOrderToBackend(cart);
			alert('Order placed successfully!');
		} catch (error) {
			alert(`Error submitting order: ${error.message}`);
		}
	};

	return (
		<TableContainer>
			<Table>
				<TableHead>
					<TableRow>
						<TableCell>Item</TableCell>
						<TableCell>Price</TableCell>
					</TableRow>
				</TableHead>
				<TableBody>
					{cart.map((item) => (
						<TableRow key={item.id}>
							<TableCell>{item.name}</TableCell>
							<TableCell>{item.price}</TableCell>
						</TableRow>
					))}
					<TableRow>
						<TableCell><Typography variant="h6">Total:</Typography></TableCell>
						<TableCell><Typography variant="h6">{totalPrice}</Typography></TableCell>
					</TableRow>
				</TableBody>
			</Table>
			<Button variant="contained" color="primary" onClick={handleCheckout}>Checkout</Button>
		</TableContainer>
	);
}

export default CartSummary;
