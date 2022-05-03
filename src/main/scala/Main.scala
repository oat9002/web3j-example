import contract.pancake.CakePool
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.DefaultGasProvider

import scala.math.BigDecimal.RoundingMode

object Main extends App {
  val bscRpcNode = "https://bsc-dataseed.binance.org"
  val cakePoolContractAddress = "0x45c54210128a065de780C4B0Df3d16664f7f859e"

  val web3j: Web3j = Web3j.build(new HttpService(bscRpcNode))
  val cakePool: CakePool = CakePool.load(cakePoolContractAddress, web3j, Credentials.create("0") ,  new DefaultGasProvider())

  val userAddress = "<your wallet address>"
  val userInfo = cakePool.userInfo(userAddress).send()
  val pricePerFullShare = cakePool.getPricePerFullShare.send()

  val shares = BigInt(userInfo.component1())
  val userBoostedShare = userInfo.component7()
  val stakedCakeWei = (BigDecimal(shares * BigInt(pricePerFullShare)) / Math.pow(10, 18)) - BigDecimal(userBoostedShare)
  val stakedCake = (stakedCakeWei / Math.pow(10, 18)).setScale(6, RoundingMode.HALF_UP)

  println(s"Your cake: $stakedCake")
}
