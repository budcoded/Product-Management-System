import React, { useState } from 'react';
import { TextField, TextareaAutosize, MenuItem, Button } from '@mui/material';
import '../css/addProduct.css'


const categories = ['Groceries', 'Fruits','Home and Kitchen', 'Fashion','Electronics', 'Sports','Books'];

function AddProduct() {
	const [productName, setProductName] = useState('');
	const [description, setDescription] = useState('');
	const [price, setPrice] = useState('');
	const [quantity, setQuantity] = useState('');
	const [category, setCategory] = useState('');

	const handleProductNameChange = (event) => {
		setProductName(event.target.value);
	};

	const handleDescriptionChange = (event) => {
		setDescription(event.target.value);
	};

	const handlePriceChange = (event) => {
		setPrice(event.target.value);
	};

	const handleQuantityChange = (event) => {
		setQuantity(event.target.value);
	};

	const handleCategoryChange = (event) => {
		setCategory(event.target.value);
	};

	const handleSubmit = (event) => {
		event.preventDefault();
		// submit form logic goes here
	};

	return (
		<form className="form-container" onSubmit={handleSubmit}>
			<label>Add Product</label>
			<TextField
				label="Product Name"
				value={productName}
				onChange={handleProductNameChange}
				fullWidth
				margin="normal"
			/>

			<TextField
				label="Price"
				value={price}
				onChange={handlePriceChange}
				fullWidth
				margin="normal"
				type="number"
				InputProps={{
					startAdornment: <span>Rs</span>,
				}}
			/>
			<TextField
				label="Quantity"
				value={quantity}
				onChange={handleQuantityChange}
				fullWidth
				margin="normal"
				type="number"
			/>
			<TextField
				select
				label="Category"
				value={category}
				onChange={handleCategoryChange}
				fullWidth
				margin="normal"
			>
				{categories.map((option) => (
					<MenuItem key={option} value={option}>
						{option}
					</MenuItem>
				))}
			</TextField>

			<TextareaAutosize
				className="MuiTextareaAutosize-root"
				label="Description"
				value={description}
				onChange={handleDescriptionChange}
				fullWidth
				margin="normal"
				minRows={5}
				maxRows={10}
				placeholder="Enter a description"
			/>
			<Button variant="contained" color="primary" type="submit">
				Add Product
			</Button>
		</form>
	);
}

export default AddProduct;
