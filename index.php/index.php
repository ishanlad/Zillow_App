
<?php
//print_r($_GET); 
//echo "<script>console.log('PHP: ".print_r($_GET);."');</script>";
//header('Access-Control-Allow-Origin: *');

//header('Access-Control-Allow-Origin: http:////www-scf.usc.edu/~srtiwari/third.html');
  //  header("Access-Control-Allow-Credentials: true");
    //header('Access-Control-Allow-Methods: GET, PUT, POST, DELETE, OPTIONS');
    //header('Access-Control-Max-Age: 1000');
    //header('Access-Control-Allow-Headers: Content-Type, Content-Range, Content-Disposition, Content-Description');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST');
	//echo "<script>console.log('PHP: ".$_GET["action"]."');</script>";
	if (isset($_GET["add"]) )
{

$ad=$_GET["add"];
$ci=$_GET["city"];
$state=$_GET["state"];
//echo $ad , $ci , $state;
$add=str_ireplace(" ", "+",$ad);
$city=str_ireplace(" ", "+",$ci);
//echo "<script>console.log('PHP: ".$add."');</script>";
$url="http://www.zillow.com/webservice/GetDeepSearchResults.htm?zws-id=X1-ZWz1b2n05uh4wb_5f91a&address=".$add."&citystatezip=".$city."%2C+".$state."&rentzestimate=true";
//echo "<script>console.log('PHP: ".$url."');</script>";
if (($response_xml_data = file_get_contents($url))===false){
      $post_data = json_encode(array('Property Type' => "NA",'Last Sold Price' => "NA",'Year built' => "NA",
	'Last Sold Date' => "NA",'Lot Size' => "NA",'Property Estimate as of' => "NA",'Property Estimate date' => "NA",
	'Finished Area' => "NA",'30 Days overall change' => "NA", 'Bathrooms' => "NA",'Bedrooms' => "NA",
	'Rent Zestimate' => "NA",'Rent Zestimate Date' => "NA",'Tax Assesment Year' => "NA",'Tax Assesment' => "NA",
	'Finished Area' => "NA", 'All Time rent Change' => "NA", 'All Time Property Range' => "NA",
     'Result Header' => "NA", 'City' => "NA", 'Street' => "NA" , 'State' => "NA" , 'Zipcode' => "NA", 'Code' => "508",
	 '30 Days Rent Change' => "NA", '30 days change arrow'=> "NA", '30 days rent change arrow'=> "NA",
	 'chart1'=> "NA", 'chart5'=> "NA",'chart10'=> "NA", ));
	  goto start ;
      
	  }
	else {
	 
   libxml_use_internal_errors(true);
   $data = simplexml_load_file($url);
   if ($data->message->code!=0)
	 {
	  
	 // echo "<center><b>".$data->message->text."</b></center>";
	 $post_data = json_encode(array('Property Type' => "NA",'Last Sold Price' => "NA",'Year built' => "NA",
	'Last Sold Date' => "NA",'Lot Size' => "NA",'Property Estimate as of' => "NA",'Property Estimate date' => "NA",
	'Finished Area' => "NA",'30 Days overall change' => "NA", 'Bathrooms' => "NA",'Bedrooms' => "NA",
	'Rent Zestimate' => "NA",'Rent Zestimate Date' => "NA",'Tax Assesment Year' => "NA",'Tax Assesment' => "NA",
	'Finished Area' => "NA", 'All Time rent Change' => "NA", 'All Time Property Range' => "NA",
     'Result Header' => "NA", 'City' => "NA", 'Street' => "NA" , 'State' => "NA" , 'Zipcode' => "NA", 'Code' => "508",
	 '30 Days Rent Change' => "NA", '30 days change arrow'=> "NA", '30 days rent change arrow'=> "NA",
	 'chart1'=> "NA", 'chart5'=> "NA",'chart10'=> "NA", ));
	  goto start ;
	 }
  
	date_default_timezone_set('America/Los_Angeles');
 	$zpid = $data->response->results->result->zpid;
 $url1="http://www.zillow.com/webservice/GetChart.htm?zws-id=X1-ZWz1b2n05uh4wb_5f91a&unit-type=percent&zpid=".$zpid."&width=600&height=300&chartDuration=1year";
 $url5="http://www.zillow.com/webservice/GetChart.htm?zws-id=X1-ZWz1b2n05uh4wb_5f91a&unit-type=percent&zpid=".$zpid."&width=600&height=300&chartDuration=5year";
 $url10="http://www.zillow.com/webservice/GetChart.htm?zws-id=X1-ZWz1b2n05uh4wb_5f91a&unit-type=percent&zpid=".$zpid."&width=600&height=300&chartDuration=10year";
 $data1 = simplexml_load_file($url1);
 $data5 = simplexml_load_file($url5);
 $data10 = simplexml_load_file($url10);
 $chart1 = $data1->response->url;
 $chart5 = $data5->response->url;
 $chart10 = $data10->response->url;
	$street= $data->response->results->result->address->street;
	$cit= $data->response->results->result->address->city;
	$stat= $data->response->results->result->address->state;
	$zipcode= $data->response->results->result->address->zipcode;
	$resultheader = $data->response->results->result->links->homedetails;
	$property = $data->response->results->result->useCode;
	$year = $data->response->results->result->yearBuilt;
	$lotsize= $data->response->results->result->lotSizeSqFt;
	$finishedarea = $data->response->results->result->finishedSqFt;
	$bathroom =  $data->response->results->result->bathrooms;
	$bedroom = $data->response->results->result->bedrooms;
	$taxyear = $data->response->results->result->taxAssessmentYear;
	$tax =  $data->response->results->result->taxAssessment;
	$lastprice = $data->response->results->result->lastSoldPrice;
	$lastdate = $data->response->results->result->lastSoldDate;
	$lastDate = date("d-M-Y", strtotime($lastdate));
	$dayschange = $data->response->results->result->zestimate->valueChange;
	$rentchange30 = $data->response->results->result->rentzestimate->valueChange;
    $propertyrangelow = $data->response->results->result->zestimate->valuationRange->low;   
    $propertyrangehigh= $data->response->results->result->zestimate->valuationRange->high;
    $zestamount = $data->response->results->result->zestimate->amount;
    $zestdate =$data->response->results->result->zestimate->{'last-updated'};
	$zestDate = date("d-M-Y", strtotime($zestdate));
	$rentzest = $data->response->results->result->rentzestimate->amount;
	$rentzestdate= $data->response->results->result->rentzestimate->{'last-updated'};
	$rentzestDate = date("d-M-Y", strtotime($rentzestdate));
	$rentzestlow = $data->response->results->result->rentzestimate->valuationRange->low;
	$rentzesthigh =$data->response->results->result->rentzestimate->valuationRange->high;
	setlocale(LC_MONETARY,"en_US");
	$arrow = array ("http://cs-server.usc.edu:45678/hw/hw6/up_g.gif", "http://cs-server.usc.edu:45678/hw/hw6/down_r.gif"," "); 
	$i=$j=0;
	if($dayschange<0)
	    {$i=1;
          $dayschange= $dayschange*-1; } 
    if($dayschange==0 || $dayschange=="" )
        $i=2;
	if($rentchange30==0 || $rentchange30=="" )
       { $j=2; }
	if($rentchange30<0)
       {$j=1;
          $rentchange30=$rentchange30*-1;
        }
		$lsp="$";
		$pe="$";
		$oc30="$";
		$atp1="$";
		$atp2="$";
		$rz="$";
		$atr1="$";
		$atr2="$";
		$adc30="$";
		$ta="$";
		$ls="sq.ft.";
		$fa="sq.ft.";
		
if ($street=="")
    $street="NA";
if ($cit=="")
    $cit="NA";
if ($stat=="")
    $stat="NA";
if ($zipcode=="")
    $zipcode="NA";
if ($property=="")
    $property="NA";
if ($year=="")
    $year="NA";
if ($lotsize==""  )
    {$lotsize="NA"; $ls="";}
	else
	{
	 $lotsize=number_format((double)$lotsize, 0, '', ',');
	}
if ($finishedarea=="" )
   { $finishedarea="NA"; $fa="";}
   else
	{
	 $finishedarea=number_format((double)$finishedarea, 0, '', ',');
	}
if ($bathroom=="")
    $bathroom="NA";
if ($taxyear=="")
    $taxyear="NA";
if ($tax=="")
    {$tax="NA"; $ta="";}
	else
	{
	$tax= number_format((double)$tax, 2, '.', ',');
	}
if ($lastprice=="")
    {$lastprice="NA"; $lsp="";}
	else
	{
	  $lastprice= number_format((double)$lastprice, 2, '.', ',');
	}
if ($lastDate=="")
    $lastDate="NA";
if ($dayschange=="")
    {$dayschange="NA"; $oc30="";}
	else
	{
	$dayschange= number_format((double)$dayschange, 2, '.', ',');
	}
if ($rentchange30=="")
   { $rentchange30="NA"; $adc30="";}
   else
	{
	$rentchange30= number_format((double)$rentchange30, 2, '.', ',');
	}
if ($propertyrangelow=="")
   { $propertyrangelow="NA"; $atp1="";}
   else
	{
	$propertyrangelow= number_format((double)$propertyrangelow, 2, '.', ',');
	}
if ($propertyrangehigh=="")
    {$propertyrangehigh="NA"; $atp2="";}
	else
	{
	$propertyrangehigh= number_format((double)$propertyrangehigh, 2, '.', ',');
	}
if ($zestamount=="")
    {$zestamount="NA"; $pe="";}
	else
	{
	$zestamount= number_format((double)$zestamount, 2, '.', ',');
	}
if ($zestDate=="")
    $zestDate="NA";
if ($rentzest=="")
   { $rentzest="NA"; $rz="";}
   else
	{
	$rentzest= number_format((double)$rentzest, 2, '.', ',');
	}
if($rentzestDate=="")
    $rentzestDate="NA";
if($rentzestlow=="")
   { $rentzestlow="NA"; $atr1="";}
   else
	{
	$rentzestlow= number_format((double)$rentzestlow, 2, '.', ',');
	}
if($rentzesthigh=="")
   { $rentzesthigh="NA";	$atr2="";}
   else
	{
	$rentzesthigh= number_format((double)$rentzesthigh, 2, '.', ',');
	}
	$lot= $lotsize." ".$ls;
	$farea=$finishedarea." ".$fa;
	$rentchangelimit= $atr1.$rentzestlow.' - '.$atr2.$rentzesthigh;
	$propertyrange= $atr1.$rentzestlow.' - '.$atr2.$rentzesthigh;
	$bathroom = (string) $bathroom;
	$bedroom = (string) $bedroom;
	$taxyear = (string) $taxyear;
	$property = (String) $property;
	$year = (String) $year;
	$resultheader = (String) $resultheader;
	$cit = (String) $cit;
	$street = (String) $street;
	$stat = (String) $stat;
	$zipcode = (String) $zipcode;
	$code =(string) $data->message->code;
	
	$post_data = json_encode(array('Property Type' => $property,'Last Sold Price' => $lsp.$lastprice,'Year built' => $year,
	'Last Sold Date' => $lastDate,'Lot Size' => $lot,'Property Estimate as of' => $pe.$zestamount,'Property Estimate date' => $zestDate,
	'Finished Area' => $farea,'30 Days overall change' => $oc30.$dayschange, 'Bathrooms' => $bathroom,'Bedrooms' => $bedroom,
	'Rent Zestimate' => $rz.$rentzest,'Rent Zestimate Date' => $rentzestDate,'Tax Assesment Year' => $taxyear,'Tax Assesment' => $ta.$tax,
	'Finished Area' => $farea, 'All Time rent Change' => $rentchangelimit, 'All Time Property Range' => $propertyrange,
     'Result Header' => $resultheader, 'City' => $cit, 'Street' => $street , 'State' => $stat , 'Zipcode' => $zipcode, 'Code' => $code,
	 '30 Days Rent Change' => $adc30.$rentchange30, '30 days change arrow'=> $arrow[$i], '30 days rent change arrow'=> $arrow[$j],
	 'chart1'=> $chart1, 'chart5'=> $chart5,'chart10'=> $chart10, ));
	
	   	
	}
	 start: 
	echo $post_data; 
	 
 }?> 
