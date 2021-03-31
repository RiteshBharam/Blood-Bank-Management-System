import React, { useState } from 'react';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import * as Yup from 'yup';
import { Formik } from 'formik';
import {
    Box,
    Button,
    Container,
    Grid,
    Link,
    TextField,
    Typography,
    makeStyles
} from '@material-ui/core';
import { Alert, AlertTitle } from "@material-ui/lab";
import Page from 'src/components/Page';
import DashboardView from 'src/views/reports/DashboardView';
import DonateButton from '../Donor/Donate1';
import axios from 'axios';




const useStyles = makeStyles((theme) => ({
    root: {
        backgroundColor: theme.palette.background.dark,
        height: '100%',
        paddingBottom: theme.spacing(3),
        paddingTop: theme.spacing(3)
    }
}));

const DonorLoginView = () => {
    const classes = useStyles();
    const navigate = useNavigate();
    const [isDashboardOpen, setDashboardOpen] = useState(false);
    const [isDonorOpen, setDonorOpen] = useState(false);
    const [formData, setFormData] = useState({ "email": '', "password": '' });
    const [successMessage, setSuccess] = useState(false);
    const [errorMessage, setError] = useState(false);
    const [respData, setRespData] = useState([]);


    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((formData) => ({
            ...formData,
            [name]: value
        }));
        // let d = Object.values(formData);
        console.log(">>>>>>>>>>>>>>>>", formData);

    };

    const redirectDashboard = () => {
        console.log("email is is...", formData.email);
        //console.log("password is is...", formData.password)
        //console.log("http://localhost:8080/donor/login?email="+formData.email+"&password="+formData.password)
        axios.post("http://localhost:8080/donor/login?email="+formData.email+"&password="+formData.password).then(resp => {
            console.log("log in resp  is...", resp)
            if(resp.status==200)
            {
              setRespData(resp.data)
              setDonorOpen(true)
            }
        })


    }

    if (isDonorOpen) {
        return (<DonateButton data={respData} />)
        // navigate('/donote', { email: formData.email, password: formData.password });
    }

    return (

        <Page
            className={classes.root}
            title="Login"
        >
            <Box
                display="flex"
                flexDirection="column"
                height="100%"
                justifyContent="center"
            >
                <Container maxWidth="sm">
                    <Formik
                        initialValues={{
                            email: 'xyz@gmail.com',
                            password: 'password'
                        }}
                        validationSchema={Yup.object().shape({
                            email: Yup.string().email('Must be a valid email').max(255).required('Email is required'),
                            password: Yup.string().max(255).required('Password is required')
                        })}
                        onSubmit={() => {
                            // navigate('/app/dashboard', { replace: true });
                            redirectDashboard();
                        }}
                    >
                        {({
                            errors,
                            handleBlur,
                            // handleChange,
                            handleSubmit,
                            isSubmitting,
                            touched,
                            values
                        }) => (
                                <form onSubmit={handleSubmit}>
                                    <Box mb={3}>
                                        <Typography
                                            color="textPrimary"
                                            variant="h2"
                                        >
                                            Sign in
                  </Typography>

                                    </Box>
                                    <Grid
                                        container
                                        spacing={3}
                                    >

                                    </Grid>
                                    <Box
                                        mt={3}
                                        mb={1}
                                    >

                                    </Box>

                                    {successMessage ? (
                                        <>
                                            <Alert severity="success">Login Successful !!</Alert>
                                        </>
                                    ) : null}
                                    {errorMessage ? (
                                        <>
                                            <Alert severity="error">Invalid Email or Password  !!</Alert>
                                        </>
                                    ) : null}
                                    <TextField
                                        error={Boolean(touched.email && errors.email)}
                                        fullWidth
                                        helperText={touched.email && errors.email}
                                        label="Email Address"
                                        margin="normal"
                                        name="email"
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        type="email"
                                        // defaultValue={values.email}
                                        variant="outlined"
                                    />
                                    <TextField
                                        error={Boolean(touched.password && errors.password)}
                                        fullWidth
                                        helperText={touched.password && errors.password}
                                        label="Password"
                                        margin="normal"
                                        name="password"
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        type="password"
                                        // defaultValue={values.password}
                                        variant="outlined"
                                    />
                                    <Box my={2}>
                                        <Button
                                            // color="primary"
                                            disabled={isSubmitting}
                                            fullWidth
                                            size="large"
                                            type="submit"
                                            variant="contained"
                                        >
                                            Sign in now
                  </Button>

                                    </Box>
                                    <Typography
                                        color="textSecondary"
                                        variant="body1"
                                    >
                                        Don&apos;t have an account?
                  {' '}
                                        <Link
                                            component={RouterLink}
                                            to="/donorform"
                                            variant="h6"
                                        >
                                            Sign up
                  </Link>
                                    </Typography>
                                </form>
                            )}
                    </Formik>
                </Container>
            </Box>
        </Page>
    );
};

export default DonorLoginView;