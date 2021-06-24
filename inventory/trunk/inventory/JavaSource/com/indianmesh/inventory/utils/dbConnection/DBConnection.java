package com.indianmesh.inventory.utils.dbConnection;

/*
 * Copyright (c) 2017, Indian Mesh Pvt. Ltd.
 * 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met,
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the inventory project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public String sCurrentLine = null;
	public String driver = null;
	public String name = null;
	public String url = null;
	public String user = null;
	public String password = null;

	public Connection getConnection() throws SQLException, SocketException, Exception {

		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------");
			e.printStackTrace();
		}

		System.out.println(
				"----------------------------------------------------------------------------------------------");

		try {

			
			  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/newinventory","newinventory","inventory@786");
			 
			/*connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "");*/

			if (connection != null)
				System.out.println("Connection Is Available!");
			else
				System.out.println("------");

		} catch (SQLException e) {
			System.out.println("--------------");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("-------------------------------");
		} else {
			System.out.println("---------------------------------------------");
		}

		return connection;
	}

	public void close(Statement pStmt, Connection connection) throws SQLException {
		if (pStmt != null)
			pStmt.close();
		System.gc();

		if (connection != null)
			connection.close();
		System.gc();
	}
}
