<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Meeting-Room Booking Confirmation Mail</title>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="top" bgcolor="#838383"
				style="background-color: #838383;"><br> <br>
				<table width="1000" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" bgcolor="#d3be6c"
							style="background-color: #d3be6c; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">
							
							<div style="font-size: 34px; color:blue;">
								<b>${message}</b>
							</div>
							
							<div style="font-size: 24px; color: #555100;">
							<br> Booking Details <br>
								<br>Booking Date  :      ${date}<br>
								<br>Booked Room   :      ${room}<br>
								<br>Booked Layout :      ${layout}<br>
								<br>Booking Status:      ${status}<br>
								<br>Deposit       :      $${deposit}<br>
								<br>Duration Range:      ${duration_range}<br>
								<br>Attendees     :      ${attendees}<br>
								<br>Payment Method:      ${payment_method}<br>
								<br>Total Amount  :      $${amount}<br>
							</div>
							
						</td>
					</tr>
				</table> <br> <br></td>
		</tr>
	</table>
</body>
</html>