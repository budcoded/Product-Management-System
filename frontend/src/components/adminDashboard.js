import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom';
import '../css/adminDashboard.css'

const useStyles = makeStyles((theme) => ({
	root: {
		flexGrow: 1,
	},
	title: {
		flexGrow: 1,
		textDecoration: 'none',
		color: 'white',
	},
	button: {
		marginLeft: theme.spacing(2),
	},
}));

export default function adminDashboard() {
	const classes = useStyles();

	return (
		<div className={classes.root}>
			<AppBar position="static">
				<Toolbar>
					<Typography variant="h6">
						<Link to="/" className={classes.title}>My App</Link>
					</Typography>
					<Button color="inherit" component={Link} to="/addProduct" className={classes.button}>Add Product</Button>
					<Button color="inherit" component={Link} to="/login" className={classes.button}>Logout</Button>
				</Toolbar>
			</AppBar>
		</div>
	);
}

