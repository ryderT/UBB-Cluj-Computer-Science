using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


/*  4 classes:
 *  SQL Connection
 *  SQL Command
 *  SQL DataReader
 *  SQL DataAdapter
 *  
 *  DataBase -> DataAdapter -> Local Application(DataSet)
 *  DataSet - > DA -> DB
 *  
 */
using System.Data.SqlClient;
using System.Data;

namespace ConsoleApp1DMBSTest1
{
    class Program
    {
        static void Main(string[] args)
        {
            //Console.Write("Hello World!");
            // for backslash add @ or add 2 //
            try
            {
                //Create connection link
                SqlConnection connection = new SqlConnection( "Data Source=LAPTOP-55QC7MMC\\SQLEXPRESS;"
                                                            + "Initial Catalog=Lab3;"
                                                            + "Integrated Security=true;" );
                //Open connection, 
                //creating command based on connection,
                //Execute and print the result,
                //Close connection
                connection.Open();

                SqlCommand command = new SqlCommand("SELECT COUNT(*) FROM MafiaBosses",connection);
                int result =(Int32) command.ExecuteScalar();
                Console.WriteLine(result);
            
                connection.Close();


                //DataSet example
                DataSet dataSet = new DataSet();
                SqlDataAdapter dataAdapter = new SqlDataAdapter("SELECT * FROM MafiaBosses",connection);
                //*
                SqlCommandBuilder commandBuilder = new SqlCommandBuilder(dataAdapter);
                //*
                dataAdapter.Fill(dataSet,"Bosses");
                //new object DataTable is created!

                DataRow dataRow = dataSet.Tables["Bosses"].NewRow();
                dataRow[0] = 300;
                dataRow["Name"] = "Tudor";
                dataRow["Surname"] = "Ardelean";
                dataRow[3] = 20;

                dataSet.Tables["Bosses"].Rows.Add(dataRow);
                dataAdapter.Update(dataSet,"Bosses");


                Console.WriteLine("Finish!");
            }
            catch(Exception e)
            {
                Console.WriteLine("Error encountered!");
                
            }
            Console.ReadKey();
        }
    }
}
