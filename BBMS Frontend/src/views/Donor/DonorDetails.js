import React, { Component } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import Typography from "@material-ui/core/Typography";
import axios from 'axios';
import {
    Container,
    Grid,
} from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/DeleteOutline';



const columns = [
    { id: "name", label: "Name", minWidth: 170 },
    { id: "address", label: "Address", minWidth: 170 },
    { id: "contactNumber", label: "Phone Number" },
    { id: "age", label: "Age" },
    { id: "bloodGroup", label: "Blood Group" },

];

const useStyles = makeStyles({
    root: {
        width: "100%"
    },
    container: {
        maxHeight: 440
    }
});

class DonorDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
           donordetails: [],
            rowsPerPage: 5,
            page: 0
        }
    }

    handleChangePage = (event, newPage) => {
        this.setState({
            page: newPage
        })
    };

    handleChangeRowsPerPage = (event) => {
        this.setState({
            rowsPerPage: event.target.value,
            page: 0
        })

    };
    componentDidMount() {
        axios.get('http://localhost:8080/donor').then(resp => {
            console.log("donor list is...", resp)
            this.setState({
                donordetails: resp.data
            })


        })
    }
    render() {
        return (
            <Paper className={useStyles.root}>
                <Typography
                    style={{
                        color: "#000000",
                        textAlign: "center",
                        opacity: "1",
                        font: "normal normal bold 32px/64px Calibri",
                        fontFamily: "Roboto",
                        color: '#707070',
                        marginBottom: "1rem",
                        marginTop: "1rem"
                    }}>
                    Donor Details
            </Typography>
                <Container maxWidth={false}>
                    <Grid
                        container
                        spacing={3}
                    >
                        <TableContainer className={useStyles.container}>
                            <Grid
                                item
                                lg={12}
                                md={12}
                                xl={9}
                                xs={12}
                            >
                                <Table stickyHeader aria-label="sticky table">
                                    <TableHead>
                                        <TableRow>
                                            {columns.map((column) => (
                                                <TableCell
                                                    key={column.id}
                                                    align={column.align}
                                                    style={{ minWidth: column.minWidth }}
                                                >
                                                    {column.label}
                                                </TableCell>

                                            ))}
                                            
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {this.state.donordetails
                                            .slice(this.state.page * this.state.rowsPerPage, this.state.page * this.state.rowsPerPage + this.state.rowsPerPage)
                                            .map((row) => {
                                                return (
                                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                                                        {columns.map((column) => {
                                                            const value = row[column.id];
                                                            return (
                                                                <TableCell key={column.id} align={column.align}>
                                                                    {column.format && typeof value === "number"
                                                                        ? column.format(value)
                                                                        : value}
                                                                </TableCell>

                                                            );

                                                        })}
                                                        
                                                    </TableRow>
                                                );
                                            })}
                                    </TableBody>
                                </Table>
                            </Grid>
                        </TableContainer>
                    </Grid>
                </Container>
                <TablePagination
                    rowsPerPageOptions={[5, 10, 15]}
                    component="div"
                    count={this.state.donordetails.length}
                    rowsPerPage={this.state.rowsPerPage}
                    page={this.state.page}
                    onChangePage={this.handleChangePage}
                    onChangeRowsPerPage={this.handleChangeRowsPerPage}
                />
            </Paper>
        );
    }
}

export default DonorDetails;