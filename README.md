# SujalAppiumAssissment

<p>To use this application, you need to have :</p>

<h2>Pre-requisites

<h4>
<ul>
<li>Java</li>
<li>IDE (IntelliJ Idea)</li>
<li>Appium</li>
</ul>
</h4>

<p><code>Note: "Appium" server should be started while using this application.</code></p>
<p>The application logins to the Khalti app passed via Configuration file.
After login, the system navigates to the "Send money" module, enter 
receiver's detail (Phone number, amount & Remarks) and proceed the transaction.
</p>

<p>Meanwhile, the application verifies if the transaction is success or not
by asserting the sender's transaction id and receiver's transaction balance.
</p>

<p>To use this application enter the sender's credential at Configuration>Config.properties.
Enter mobile number (username) and password (password). Simultaneously, enter receiver's credential email (username2) 
and password (password2) as well.
</p>